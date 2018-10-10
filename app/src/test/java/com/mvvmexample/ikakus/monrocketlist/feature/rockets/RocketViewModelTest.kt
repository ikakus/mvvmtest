package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import com.mvvmexample.ikakus.data.datasource.rocket.RocketsDataSource
import com.mvvmexample.ikakus.data.entities.RocketEntity
import com.mvvmexample.ikakus.monrocketlist.common.schedulers.ImmediateSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class RocketViewModelTest {

  lateinit var vModel: RocketsViewModel
  @Mock
  private lateinit var tasksRepository: RocketsDataSource

  private lateinit var list: List<RocketEntity>

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    vModel = RocketsViewModel(ImmediateSchedulerProvider(), tasksRepository)
     list = listOf(RocketEntity(1, "asd","asd","asd","asd",false))
    Mockito.`when`(tasksRepository.getRockets()).thenReturn(Single.just(list))
  }

  @Test
  fun dataLoaded() {
    tasksRepository
        .getRockets()
        .test()
        .assertValues(list)
        .assertSubscribed()
        .assertComplete()
        .assertNoErrors()
  }
}