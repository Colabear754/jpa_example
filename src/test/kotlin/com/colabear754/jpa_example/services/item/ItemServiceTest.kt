package com.colabear754.jpa_example.services.item

import com.colabear754.jpa_example.TestContainer
import com.colabear754.jpa_example.common.ItemType
import com.colabear754.jpa_example.dto.member.item.RegistItemRequest
import com.colabear754.jpa_example.entities.item.Album
import com.colabear754.jpa_example.entities.item.Book
import com.colabear754.jpa_example.entities.item.Movie
import com.colabear754.jpa_example.exceptions.NotEnoughStockException
import com.colabear754.jpa_example.repositories.item.AlbumRepository
import com.colabear754.jpa_example.repositories.item.BookRepository
import com.colabear754.jpa_example.repositories.item.ItemRepository
import com.colabear754.jpa_example.repositories.item.MovieRepository
import com.colabear754.jpa_example.util.findByIdOrThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

@TestContainer
class ItemServiceTest @Autowired constructor(
    private val itemService: ItemService,
    private val itemRepository: ItemRepository,
    private val albumRepository: AlbumRepository,
    private val bookRepository: BookRepository,
    private val movieRepository: MovieRepository
) {
    @AfterEach
    fun clear() {
        movieRepository.deleteAll()
        bookRepository.deleteAll()
        albumRepository.deleteAll()
        itemRepository.deleteAll()
    }

    @Test
    fun 상품추가() {
        // given
        val album = RegistItemRequest("album", 1000, 1, ItemType.ALBUM, mutableMapOf("artist" to "artist", "etc" to "etc"), "createdBy")
        val book = RegistItemRequest("book", 1000, 1, ItemType.BOOK, mutableMapOf("author" to "author", "isbn" to "isbn"), "createdBy")
        val movie = RegistItemRequest("movie", 1000, 1, ItemType.MOVIE, mutableMapOf("director" to "director", "actor" to "actor"), "createdBy")
        // when
        itemService.registNewItem(album)
        itemService.registNewItem(book)
        itemService.registNewItem(movie)
        // then
        val savedItems = itemRepository.findAll()
        assertThat(savedItems).hasSize(3)
        assertThat(savedItems.filterIsInstance<Album>()[0]::class).isEqualTo(Album::class)
        assertThat(savedItems.filterIsInstance<Book>()[0]::class).isEqualTo(Book::class)
        assertThat(savedItems.filterIsInstance<Movie>()[0]::class).isEqualTo(Movie::class)
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

    @Test
    fun `다수의 상품 추가`() {
        // given
        val album = Album("album", 1000, 1, "artist", "etc", "createdBy", "lastModifiedBy")
        val book = Book("book", 1000, 1, "author", "isbn", "createdBy", "lastModifiedBy")
        val movie = Movie("movie", 1000, 1, "director", "actor", "createdBy", "lastModifiedBy")
        // when
        itemService.registNewItems(listOf(album, book, movie))
        // then
        val savedItems = itemRepository.findAll()
        assertThat(savedItems).hasSize(3)
        assertThat(savedItems.filterIsInstance<Album>()[0]::class).isEqualTo(Album::class)
        assertThat(savedItems.filterIsInstance<Book>()[0]::class).isEqualTo(Book::class)
        assertThat(savedItems.filterIsInstance<Movie>()[0]::class).isEqualTo(Movie::class)
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

    @Test
    fun 상품수정() {
        // given
        val album = itemRepository.save(Album("album", 1000, 1, "artist", "etc", "createdBy", "lastModifiedBy"))
        // when
        val savedAlbum = itemRepository.findByIdOrThrow(album.id)
        val newAlbum = Album("newAlbum", 2000, 2, "newArtist", "newEtc", "newCreatedBy", "newLastModifiedBy")
        itemService.updateItem(savedAlbum.id!!, newAlbum)
        // then
        val updatedAlbum = itemRepository.findByIdOrThrow(album.id) as Album
        assertThat(updatedAlbum.name).isEqualTo("newAlbum")
        assertThat(updatedAlbum.price).isEqualTo(2000)
        assertThat(updatedAlbum.stockQuantity).isEqualTo(2)
        assertThat(updatedAlbum.artist).isEqualTo("newArtist")
        assertThat(updatedAlbum.etc).isEqualTo("newEtc")
    }

    @Test
    fun 상품재입고() {
        // given
        val book = itemRepository.save(Book("album", 1000, 1, "artist", "etc", "createdBy", "lastModifiedBy"))
        // when
        val savedBook = itemRepository.findByIdOrThrow(book.id)
        itemService.restock(savedBook.id!!, 10)
        // then
        val updatedBook = itemRepository.findByIdOrThrow(book.id) as Book
        assertThat(updatedBook.stockQuantity).isEqualTo(11)
    }

    @Test
    fun 상품판매() {
        // given
        val movie = itemRepository.save(Movie("album", 1000, 1, "artist", "etc", "createdBy", "lastModifiedBy"))
        // when
        val savedMovie = itemRepository.findByIdOrThrow(movie.id)
        itemService.sell(savedMovie.id!!, 1)
        // then
        val updatedMovie = itemRepository.findByIdOrThrow(movie.id) as Movie
        assertThat(updatedMovie.stockQuantity).isEqualTo(0)
    }

    @Test
    fun `상품 수량이 부족할 때`() {
        // given
        val movie = itemRepository.save(Movie("album", 1000, 1, "artist", "etc", "createdBy", "lastModifiedBy"))
        // when
        // then
        val savedMovie = itemRepository.findByIdOrThrow(movie.id)
        assertThrows<NotEnoughStockException> {
            itemService.sell(savedMovie.id!!, 2)
        }.also {
            assertThat(it.message).isEqualTo("재고가 부족합니다. 현재 재고: 1, 요청 수량: 2")
        }
    }
}