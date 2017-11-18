/**
 * Created by rajendarreddy on 6/28/17.
 */
package com.rajendarreddyj.spring;

import java.io.IOException;
import java.net.ServerSocket;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;

import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@Configuration
public class HazelCastConfig {
    private String sessionMapName = "spring:session:sessions";

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private static int getAvailablePort() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(0);
            return socket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    @Bean(destroyMethod = "shutdown")
    public HazelcastInstance hazelcastInstance() {
        com.hazelcast.config.Config cfg = new com.hazelcast.config.Config();
        NetworkConfig netConfig = new NetworkConfig();
        netConfig.setPort(getAvailablePort());
        cfg.setNetworkConfig(netConfig);
        SerializerConfig serializer = new SerializerConfig().setTypeClass(Object.class).setImplementation(new ObjectStreamSerializer());
        cfg.getSerializationConfig().addSerializerConfig(serializer);
        MapConfig mc = new MapConfig();
        mc.setName(this.sessionMapName);

        mc.setMaxIdleSeconds(60);
        cfg.addMapConfig(mc);

        return Hazelcast.newHazelcastInstance(cfg);
    }

    @Bean
    public SessionRemovedListener removeListener() {
        return new SessionRemovedListener(this.eventPublisher);
    }

    @Bean
    public SessionEvictedListener evictListener() {
        return new SessionEvictedListener(this.eventPublisher);
    }

    @Bean
    public MapSessionRepository sessionRepository(final HazelcastInstance instance, final SessionRemovedListener removeListener,
            final SessionEvictedListener evictListener) {
        IMap<String, ExpiringSession> sessions = instance.getMap(this.sessionMapName);
        sessions.addEntryListener(removeListener, false);
        sessions.addEntryListener(evictListener, false);
        return new MapSessionRepository(sessions);
    }

    @Bean
    public <S extends ExpiringSession> SessionRepositoryFilter<? extends ExpiringSession> springSessionRepositoryFilter(
            final SessionRepository<S> sessionRepository, final ServletContext servletContext) {
        SessionRepositoryFilter<S> sessionRepositoryFilter = new SessionRepositoryFilter<>(sessionRepository);
        sessionRepositoryFilter.setServletContext(servletContext);
        return sessionRepositoryFilter;
    }
}
