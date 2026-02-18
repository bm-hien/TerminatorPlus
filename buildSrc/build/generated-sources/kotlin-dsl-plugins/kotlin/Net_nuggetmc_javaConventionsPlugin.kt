/**
 * Precompiled [net.nuggetmc.java-conventions.gradle.kts][Net_nuggetmc_java_conventions_gradle] script plugin.
 *
 * @see Net_nuggetmc_java_conventions_gradle
 */
public
class Net_nuggetmc_javaConventionsPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Net_nuggetmc_java_conventions_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
