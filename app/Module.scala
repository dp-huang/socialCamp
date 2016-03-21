import com.google.inject.AbstractModule
import repositories.asset.AssetRepoComponentImpl
import repositories.social.SocialRepoComponentImpl
import repositories.user.UserRepoComponentImpl
import services.asset.{AssetServiceComponent, AssetServiceComponentImpl}
import services.social.{SocialServiceComponent, SocialServiceComponentImpl}
import services.user.{UserServiceComponent, UserServiceComponentImpl}

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure() = {

    val userComp = new UserServiceComponentImpl with UserRepoComponentImpl
    bind(classOf[UserServiceComponent]).toInstance(userComp)

    val assetComp = new AssetServiceComponentImpl with AssetRepoComponentImpl
    bind(classOf[AssetServiceComponent]).toInstance(assetComp)

    val socialComp = new SocialServiceComponentImpl with SocialRepoComponentImpl
    bind(classOf[SocialServiceComponent]).toInstance(socialComp)
  }

}
