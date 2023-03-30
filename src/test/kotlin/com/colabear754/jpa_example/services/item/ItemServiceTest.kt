package com.colabear754.jpa_example.services.item

import com.colabear754.jpa_example.entities.item.Album
import com.colabear754.jpa_example.entities.item.Book
import com.colabear754.jpa_example.entities.item.Item
import com.colabear754.jpa_example.entities.item.Movie
import com.colabear754.jpa_example.repositories.item.AlbumRepository
import com.colabear754.jpa_example.repositories.item.BookRepository
import com.colabear754.jpa_example.repositories.item.ItemRepository
import com.colabear754.jpa_example.repositories.item.MovieRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ItemServiceTest @Autowired constructor(
    private val itemService: ItemService,
    private val itemRepository: ItemRepository,
    private val albumRepository: AlbumRepository,
    private val bookRepository: BookRepository,
    private val movieRepository: MovieRepository
) {
    @Test
    fun 상품추가() {
        // given
        val album = Album("album", 1000, 1, "artist", "etc", "createdBy", "lastModifiedBy")
        val book = Book("book", 1000, 1, "author", "isbn", "createdBy", "lastModifiedBy")
        val movie = Movie("movie", 1000, 1, "director", "actor", "createdBy", "lastModifiedBy")
        // when
        itemService.registNewItem(album)
        itemService.registNewItem(book)
        itemService.registNewItem(movie)
        // then
        val savedItems = itemRepository.findAll()
        assertThat(savedItems).hasSize(3)
        assertThat(savedItems.filter { it::class == Album::class }[0]::class).isEqualTo(Album::class)
        assertThat(savedItems.filter { it::class == Book::class }[0]::class).isEqualTo(Book::class)
        assertThat(savedItems.filter { it::class == Movie::class }[0]::class).isEqualTo(Movie::class)
        val savedAlbums = albumRepository.findAll()
        assertThat(savedAlbums).hasSize(1)
        assertThat(savedAlbums[0].name).isEqualTo("album")
        assertThat(savedAlbums[0].price).isEqualTo(1000)
        val savedBooks = bookRepository.findAll()
        assertThat(savedBooks).hasSize(1)
        assertThat(savedBooks[0].name).isEqualTo("book")
        assertThat(savedBooks[0].price).isEqualTo(1000)
        val savedMovies = movieRepository.findAll()
        assertThat(savedMovies).hasSize(1)
        assertThat(savedMovies[0].name).isEqualTo("movie")
        assertThat(savedMovies[0].price).isEqualTo(1000)
    }
}