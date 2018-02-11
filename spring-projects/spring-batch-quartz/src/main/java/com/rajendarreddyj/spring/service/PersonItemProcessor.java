package com.rajendarreddyj.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.rajendarreddyj.spring.bean.Person;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

/*    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        new FlatFileItemReader<>();
        File[] files = new File("/home/rajendarreddy/test").listFiles();
        if (files.length < 1) {
            stepExecution.setExitStatus(ExitStatus.COMPLETED);
            stepExecution.setStatus(BatchStatus.COMPLETED);
        }
    }*/

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
