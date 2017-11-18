package com.rajendarreddyj.spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.session.ExpiringSession;
import org.springframework.session.events.SessionDestroyedEvent;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryEvictedListener;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class SessionEvictedListener implements EntryEvictedListener<String, ExpiringSession> {

    private ApplicationEventPublisher eventPublisher;

    public SessionEvictedListener(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void entryEvicted(final EntryEvent<String, ExpiringSession> event) {
        System.out.println("Session removed: " + event);
        this.eventPublisher.publishEvent(new SessionDestroyedEvent(this, event.getKey()));
    }

}