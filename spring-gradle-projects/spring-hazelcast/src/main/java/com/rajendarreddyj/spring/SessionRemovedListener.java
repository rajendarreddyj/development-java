package com.rajendarreddyj.spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.session.ExpiringSession;
import org.springframework.session.events.SessionDestroyedEvent;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryRemovedListener;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class SessionRemovedListener implements EntryRemovedListener<String, ExpiringSession> {

    private ApplicationEventPublisher eventPublisher;

    public SessionRemovedListener(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void entryRemoved(final EntryEvent<String, ExpiringSession> event) {
        System.out.println("Session removed: " + event);
        this.eventPublisher.publishEvent(new SessionDestroyedEvent(this, event.getKey()));
    }

}
