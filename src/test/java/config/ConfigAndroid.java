package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",          // ⚠️ ВАЖНО: именно так, БЕЗ имени файла!
        "classpath:config.properties" // ⚠️ А имя файла пишется только здесь
})
public interface ConfigAndroid extends Config {

    @Key("browserstack.user")
    String user();

    @Key("browserstack.key")
    String key();

    @Key("browserstack.hubUrl")
    String hubUrl();

    @Key("android.deviceName")
    String androidDeviceName();

    @Key("android.osVersion")
    String androidOsVersion();

    @Key("android.app")
    String androidApp();

    @Key("appium.version")
    String appiumVersion();

    @Key("project.name")
    String projectName();

    @Key("build.name")
    String buildName();
}