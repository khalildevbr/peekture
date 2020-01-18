package dev.khalil.peekture.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.khalil.peekture.PeektureApp
import dev.khalil.peekture.di.builder.ActivityBuilder
import dev.khalil.peekture.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class
    )
)
interface AppComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: PeektureApp)
}