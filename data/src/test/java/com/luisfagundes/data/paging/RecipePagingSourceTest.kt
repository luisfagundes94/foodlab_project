package com.luisfagundes.data.paging

import androidx.paging.PagingSource
import com.luisfagundes.data.datasources.RemoteRecipeDataSource
import com.luisfagundes.data.factory.RecipeListBodyFactory
import com.luisfagundes.domain.mockdata.RecipeFactory
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.testing.rule.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class RecipePagingSourceTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val remoteDataSource: RemoteRecipeDataSource = mockk()
    private val defaultQueryMap = mapOf("" to "")
    private lateinit var recipePagingSource: RecipePagingSource

    @Before
    fun setUp() {
        recipePagingSource = RecipePagingSource(
            remoteDataSource,
            defaultQueryMap
        )
    }

    @Test
    fun `SHOULD return a success load result when load is called`() = runTest {
        // Arrange
        coEvery {
            remoteDataSource.fetchRecipes(any())
        } returns RecipeListBodyFactory.create()

        // Act
        val result = recipePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        // Assert
        val expectedData = listOf(
            RecipeFactory.create(),
            RecipeFactory.create(),
            RecipeFactory.create()
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expectedData,
                prevKey = null,
                nextKey = 10
            ),
            result
        )
    }

    @Test(expected = RuntimeException::class)
    fun `SHOULD return an error load when load is called`() = runTest {
        // Arrange
        val exception = RuntimeException()
        coEvery { remoteDataSource.fetchRecipes(any()) } throws exception

        // Act
        val result = recipePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, Recipe>(exception),
            result
        )
    }

}