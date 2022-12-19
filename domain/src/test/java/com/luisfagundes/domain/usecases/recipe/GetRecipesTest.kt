package com.luisfagundes.domain.usecases.recipe

import androidx.paging.PagingConfig
import com.google.common.truth.Truth
import com.luisfagundes.domain.mockdata.PagingSourceFactory
import com.luisfagundes.domain.mockdata.RecipeFactory
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.testing.rule.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

@ExperimentalCoroutinesApi
internal class GetRecipesTest {
    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()

    private val repository: RecipeRepository = mockk()
    private val recipeFactory = RecipeFactory()
    private val pagingSourceFactory = PagingSourceFactory().create(listOf(
        recipeFactory.create(),
        recipeFactory.create(),
        recipeFactory.create()
    ))
    private lateinit var getRecipes: GetRecipes

    @Before
    fun setUp() {
        getRecipes = GetRecipes(repository)
    }

    @Test
    fun `SHOULD validate flow paging data WHEN getRecipes is called`() = runTest {
        // Arrange
        val params = mapOf("sort" to "popularity")
        val pagingConfig = PagingConfig(10)

        coEvery { repository.fetchRecipes(params) } returns pagingSourceFactory

        // Act
        val result = getRecipes(
            GetRecipes.Params(
                params = params, pagingConfig = pagingConfig
            )
        )

        // Assert
        Truth.assertThat(result.first()).isNotNull()
    }
}