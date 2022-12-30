package com.luisfagundes.data.repositories

import androidx.paging.PagingSource
import com.luisfagundes.data.paging.RecipePagingSource
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.mockdata.RecipeFactory
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.testing.rule.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RecipeRepositoryImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val pagingSource: RecipePagingSource = mockk()
    private val dataSource: RecipeDataSource = mockk()
    private lateinit var repository: RecipeRepository

    @Before
    fun setUp() {
        repository = RecipeRepositoryImpl(dataSource)
    }

    @Test
    fun `SHOULD call pagingSource WHEN fetching recipes`() {
        // Arrange
        val pagingSourceResult = PagingSource.LoadResult.Page(
            data = listOf(
                RecipeFactory.create(),
                RecipeFactory.create(),
                RecipeFactory.create()
            ),
            prevKey = null,
            nextKey = 10
        )
        coEvery { pagingSource.load(any()) } returns pagingSourceResult

        // Act
        repository.fetchRecipes(mapOf("" to ""))

        // Assert
        coVerify(exactly = 1) { pagingSource.load(any()) }
    }
}