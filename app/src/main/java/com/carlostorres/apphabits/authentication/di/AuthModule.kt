package com.carlostorres.apphabits.authentication.di

import com.carlostorres.apphabits.authentication.data.matcher.EmailMatcherImpl
import com.carlostorres.apphabits.authentication.data.repository.AuthenticationRepoImpl
import com.carlostorres.apphabits.authentication.domain.matcher.EmailMatcher
import com.carlostorres.apphabits.authentication.domain.repository.AuthenticationRepo
import com.carlostorres.apphabits.authentication.domain.usecases.LoginUseCases
import com.carlostorres.apphabits.authentication.domain.usecases.LoginWithEmailUseCase
import com.carlostorres.apphabits.authentication.domain.usecases.ValidateEmailUseCase
import com.carlostorres.apphabits.authentication.domain.usecases.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    
    @Provides
    @Singleton
    fun provideAuthRepo(): AuthenticationRepo = AuthenticationRepoImpl()

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher = EmailMatcherImpl()

     @Singleton
     @Provides
     fun provideLoginUseCases(repo: AuthenticationRepo, emailMatcher: EmailMatcher) : LoginUseCases{
         return LoginUseCases(
             loginWithEmailUseCase = LoginWithEmailUseCase(repo),
             validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
             validatePasswordUseCase = ValidatePasswordUseCase()
         )
     }
    
}