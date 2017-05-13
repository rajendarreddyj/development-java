package com.rajendarreddyj.springbatchlistener.listener;

import java.util.logging.Logger;

import org.springframework.batch.core.ItemReadListener;

import com.rajendarreddyj.springbatchlistener.model.User;

/**
 * @author rajendarreddy
 *
 */
public class ReadListener implements ItemReadListener<User> {
    private static final Logger logger = Logger.getAnonymousLogger();
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.batch.core.ItemReadListener#afterRead(java.lang.Object
     * )
     */
    @Override
    public void afterRead(final User user) {
        logger.info("After reading an item: " + user.toString());

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.batch.core.ItemReadListener#beforeRead()
     */
    @Override
    public void beforeRead() {
        logger.info("Before reading an item");

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
        logger.info("Error occurred while reading an item!");

    }

}
