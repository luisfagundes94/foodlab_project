package com.luisfagundes.recipes

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.luisfagundes.domain.usecases.recipe.GetRecipes
import com.luisfagundes.domain.mockdata.RecipeFactory
import com.luisfagundes.testing.rule.TestCoroutineRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class RecipesViewModelTest {
    @get:Rule
    val coroutineRule = TestCoroutineRule()

    private val recipePagingData = PagingData.from(
        listOf(
            RecipeFactory.create(),
            RecipeFactory.create(),
            RecipeFactory.create()
        )
    )

    private val getRecipes: GetRecipes = mockk()
    private lateinit var viewModel: RecipesViewModel

    @Before
    fun setUp() {
        viewModel = RecipesViewModel(getRecipes)
    }

    @Test
    fun `SHOULD validate useCase execution WHEN loading recipes`() =
        runTest {
            // Arrange
            every { getRecipes(any()) } returns flowOf(recipePagingData)

            // Act
            viewModel.getRecipesPagingData()

            // Assert
            verify(exactly = 1) { getRecipes(any()) }
        }

    @Test
    fun `SHOULD validate the paging data object values when calling recipesPagingData`() =
        runTest {
            // Arrange
            every { getRecipes(any()) } returns flowOf(recipePagingData)

            // Act
            val result = viewModel.getRecipesPagingData()

            // Assert
            result.test {
                awaitItem().apply {
                    Truth.assertThat(this).isNotNull()
                }
            }
        }

    @Test(expected = RuntimeException::class)
    fun `SHOULD throw an exception when useCase execution returns an exception`() =
        runTest {
            // Arrange
            every { getRecipes(any()) } throws RuntimeException()

            // Act and Assert
            viewModel.getRecipesPagingData()
        }
}