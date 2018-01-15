package com.rajendarreddyj.rxjava;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ReactivexTest {
    public static void main(String[] args) {
        //Hello World program
        Flowable.just("Hello world").subscribe(System.out::println);
        /*
        If your platform doesn't support Java 8 lambdas (yet), you have to create an inner class of Consumer manually:
        */
        Flowable.just("Hello world")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });
        /*
        io.reactivex.Flowable: 0..N flows, supporting Reactive-Streams and backpressure
        io.reactivex.Observable: 0..N flows, no backpressure
        io.reactivex.Single: a flow of exactly 1 item or an error
        io.reactivex.Completable: a flow without items but only a completion or error signal
        io.reactivex.Maybe: a flow with no items, exactly one item or an error
        */
        /*
        One of the common use cases for RxJava is to run some computation, network request on a background thread
        and show the results (or error) on the UI thread:
        */
        try {
            Flowable.fromCallable(() -> {
                Thread.sleep(1000); //  imitate expensive computation
                return "Done";
            }).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.single())
                    .subscribe(System.out::println, Throwable::printStackTrace);

            Thread.sleep(2000); // <--- wait for the flow to finish
        } catch (Exception e) {

        }

        /*
        This style of chaining methods is called a fluent API which resembles the builder pattern.
        However, RxJava's reactive types are immutable; each of the method calls returns a new Flowable with added
        behavior. To illustrate, the example can be rewritten as follows:
        */
        try {
            Flowable<String> source = Flowable.fromCallable(() -> {
                Thread.sleep(1000); //  imitate expensive computation
                return "Done";
            });

            Flowable<String> runBackground = source.subscribeOn(Schedulers.io());

            Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());

            showForeground.subscribe(System.out::println, Throwable::printStackTrace);

            Thread.sleep(2000);
        } catch (Exception e) {

        }

        /*
        Flows in RxJava are sequential in nature split into processing stages that may run concurrently with each other:
        */

        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);

       /*
       Processing the numbers 1 to 10 in parallel is a bit more involved:
       */

        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);

       /*
       Starting from 2.0.5, there is an experimental operator parallel() and type ParallelFlowable
       that helps achieve the same parallel processing pattern:
       */

        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);

        /*
        flatMap is a powerful operator and helps in a lot of situations.
        For example, given a service that returns a Flowable,
        we'd like to call another service with values emitted by the first service:
         */

        /*
        Flowable<Inventory> inventorySource = warehouse.getInventoryAsync();

        inventorySource.flatMap(inventoryItem ->
                erp.getDemandAsync(inventoryItem.getId())
                        .map(demand
                                -> System.out.println("Item " + inventoryItem.getName() + " has demand " + demand));
        ).subscribe();
        */
    }
}