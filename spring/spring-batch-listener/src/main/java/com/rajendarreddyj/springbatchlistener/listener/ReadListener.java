package com.rajendarreddyj.springbatchlistener.listener;

import org.springframework.batch.core.ItemReadListener;

import com.rajendarreddyj.springbatchlistener.model.User;

/**
 * @author rajendarreddy
 *
 */
public class ReadListener implements ItemReadListener<User> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.batch.core.ItemReadListener#afterRead(java.lang.Object
     * )
     */
    @Override
    public void afterRead(final User user) {
        System.out.println("After reading an item: " + user.toString());

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.batch.core.ItemReadListener#beforeRead()
     */
    @Override
    public void beforeRead() {
        System.out.println("Before reading an item");

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.batch.core.ItemReadListener#onReadError(java.lang
     * .Exception)
     */
    @Override
    public void onReadError(final Exception ex) {
        System.out.println("Error occurred while reading an item!");

    }

}
