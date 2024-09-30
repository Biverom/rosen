package com.notjang.rosen.platform;

import com.notjang.rosen.Constants;
import com.notjang.rosen.platform.services.IPlatformHelper;
import com.notjang.rosen.platform.services.ISoundRegistry;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final ISoundRegistry SOUND_REGISTRY = load(ISoundRegistry.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}