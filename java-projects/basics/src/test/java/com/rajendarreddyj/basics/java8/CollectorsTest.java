package com.rajendarreddyj.basics.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * @author rajendarreddy
 *
 */
public class CollectorsTest {
    private final List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");

    @Test
    public void whenCollectingToList_shouldCollectToList() throws Exception {
        final List<String> result = this.givenList.stream().collect(Collectors.toList());
        Assertions.assertThat(result).containsAll(this.givenList);
    }

    @Test
    public void whenCollectingToList_shouldCollectToSet() throws Exception {
        final Set<String> result = this.givenList.stream().collect(Collectors.toSet());
        Assertions.assertThat(result).containsAll(this.givenList);
    }

    @Test
    public void whenCollectingToCollection_shouldCollectToCollection() throws Exception {
        final List<String> result = this.givenList.stream().collect(Collectors.toCollection(LinkedList::new));
        Assertions.assertThat(result).containsAll(this.givenList).isInstanceOf(LinkedList.class);
    }

    @Test
    public void whenCollectingToImmutableCollection_shouldThrowException() throws Exception {
        Assertions.assertThatThrownBy(() -> {
            this.givenList.stream().collect(Collectors.toCollection(ImmutableList::of));
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void whenCollectingToMap_shouldCollectToMap() throws Exception {
        final Map<String, Integer> result = this.givenList.stream().collect(Collectors.toMap(Function.identity(), String::length));
        Assertions.assertThat(result).containsEntry("a", 1).containsEntry("bb", 2).containsEntry("ccc", 3).containsEntry("dd", 2);
    }

    @Test
    public void whenCollectingToMap_shouldCollectToMapMerging() throws Exception {
        final Map<String, Integer> result = this.givenList.stream().collect(Collectors.toMap(Function.identity(), String::length, (i1, i2) -> i1));
        Assertions.assertThat(result).containsEntry("a", 1).containsEntry("bb", 2).containsEntry("ccc", 3).containsEntry("dd", 2);
    }

    @Test
    public void whenCollectingAndThen_shouldCollect() throws Exception {
        final List<String> result = this.givenList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        Assertions.assertThat(result).containsAll(this.givenList).isInstanceOf(ImmutableList.class);
    }

    @Test
    public void whenJoining_shouldJoin() throws Exception {
        final String result = this.givenList.stream().collect(Collectors.joining());
        Assertions.assertThat(result).isEqualTo("abbcccdd");
    }

    @Test
    public void whenJoiningWithSeparator_shouldJoinWithSeparator() throws Exception {
        final String result = this.givenList.stream().collect(Collectors.joining(" "));
        Assertions.assertThat(result).isEqualTo("a bb ccc dd");
    }

    @Test
    public void whenJoiningWithSeparatorAndPrefixAndPostfix_shouldJoinWithSeparatorPrePost() throws Exception {
        final String result = this.givenList.stream().collect(Collectors.joining(" ", "PRE-", "-POST"));
        Assertions.assertThat(result).isEqualTo("PRE-a bb ccc dd-POST");
    }

    @Test
    public void whenPartitioningBy_shouldPartition() throws Exception {
        final Map<Boolean, List<String>> result = this.givenList.stream().collect(Collectors.partitioningBy(s -> s.length() > 2));
        Assertions.assertThat(result).containsKeys(true, false).satisfies(booleanListMap -> {
            Assertions.assertThat(booleanListMap.get(true)).contains("ccc");
            Assertions.assertThat(booleanListMap.get(false)).contains("a", "bb", "dd");
        });
    }

    @Test
    public void whenCounting_shouldCount() throws Exception {
        final Long result = this.givenList.stream().collect(Collectors.counting());
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    public void whenSummarizing_shouldSummarize() throws Exception {
        final DoubleSummaryStatistics result = this.givenList.stream().collect(Collectors.summarizingDouble(String::length));
        Assertions.assertThat(result.getAverage()).isEqualTo(2);
        Assertions.assertThat(result.getCount()).isEqualTo(4);
        Assertions.assertThat(result.getMax()).isEqualTo(3);
        Assertions.assertThat(result.getMin()).isEqualTo(1);
        Assertions.assertThat(result.getSum()).isEqualTo(8);
    }

    @Test
    public void whenAveraging_shouldAverage() throws Exception {
        final Double result = this.givenList.stream().collect(Collectors.averagingDouble(String::length));
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void whenSumming_shouldSum() throws Exception {
        final Double result = this.givenList.stream().filter(i -> true).collect(Collectors.summingDouble(String::length));
        Assertions.assertThat(result).isEqualTo(8);
    }

    @Test
    public void whenMaxingBy_shouldMaxBy() throws Exception {
        final Optional<String> result = this.givenList.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
        Assertions.assertThat(result).isPresent().hasValue("dd");
    }

    @Test
    public void whenGroupingBy_shouldGroupBy() throws Exception {
        final Map<Integer, Set<String>> result = this.givenList.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        Assertions.assertThat(result).containsEntry(1, Sets.newHashSet("a")).containsEntry(2, Sets.newHashSet("bb", "dd")).containsEntry(3,
                Sets.newHashSet("ccc"));
    }

    @Test
    public void whenCreatingCustomCollector_shouldCollect() throws Exception {
        final ImmutableSet<String> result = this.givenList.stream().collect(toImmutableSet());
        Assertions.assertThat(result).isInstanceOf(ImmutableSet.class).contains("a", "bb", "ccc", "dd");
    }

    private static <T> ImmutableSetCollector<T> toImmutableSet() {
        return new ImmutableSetCollector<>();
    }

    private static class ImmutableSetCollector<T> implements Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> {
        @Override
        public Supplier<ImmutableSet.Builder<T>> supplier() {
            return ImmutableSet::builder;
        }

        @Override
        public BiConsumer<ImmutableSet.Builder<T>, T> accumulator() {
            return ImmutableSet.Builder::add;
        }

        @Override
        public BinaryOperator<ImmutableSet.Builder<T>> combiner() {
            return (left, right) -> left.addAll(right.build());
        }

        @Override
        public Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher() {
            return ImmutableSet.Builder::build;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Sets.immutableEnumSet(Characteristics.UNORDERED);
        }
    }
}
