package com.rajendarreddyj.spring.batch.config;

import java.io.File;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.rajendarreddyj.spring.bean.Person;
import com.rajendarreddyj.spring.service.JobCompletionNotificationListener;
import com.rajendarreddyj.spring.service.PersonItemProcessor;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]
    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        File[] files = new File("/home/rajendarreddy/test").listFiles();
        // If this pathname does not denote a directory, then listFiles() returns null.

        // reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setResource(new FileSystemResource(files[0]));
        reader.setStrict(false);
        reader.setLineMapper(new DefaultLineMapper<Person>() {
            {
                this.setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        this.setNames(new String[] { "firstName", "lastName" });
                    }
                });
                this.setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
                    {
                        this.setTargetType(Person.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    /*  @Bean
    public MyDecider myDecider() {
        return new MyDecider();
    }
    
    @Bean
    public SkipCheckingListener skipCheckingListener() {
        return new SkipCheckingListener();
    }*/

    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setDataSource(this.dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    @Bean
    public FileCheckTaskLet fileCheckTaskLet() {
        return new FileCheckTaskLet();
    }

    // tag::jobstep[]
    @Bean
    public Job importUserJob(final JobCompletionNotificationListener listener) {
        return this.jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).start(this.prestep()).on("PROCESS")
                .to(this.step1()).from(this.prestep()).on("COMPLETED").end().end().build();
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1").<Person, Person> chunk(10).processor(this.processor()).reader(this.reader()).writer(this.writer())
                /*listener(skipCheckingListener())*/.build();
    }

    @Bean
    public Step prestep() {
        return this.stepBuilderFactory.get("prestep").tasklet(this.fileCheckTaskLet()).build();
    }

    // end::jobstep[]
}
