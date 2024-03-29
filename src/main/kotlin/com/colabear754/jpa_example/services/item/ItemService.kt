package com.colabear754.jpa_example.services.item

import com.colabear754.jpa_example.domain.entities.item.Item
import com.colabear754.jpa_example.dto.item.ItemRequest
import com.colabear754.jpa_example.repositories.item.AlbumRepository
import com.colabear754.jpa_example.repositories.item.BookRepository
import com.colabear754.jpa_example.repositories.item.ItemRepository
import com.colabear754.jpa_example.repositories.item.MovieRepository
import com.colabear754.jpa_example.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val albumRepository: AlbumRepository,
    private val bookRepository: BookRepository,
    private val movieRepository: MovieRepository
) {
    @Transactional
    fun registNewItem(request: ItemRequest) = itemRepository.save(Item.from(request))

    @Transactional
    fun registNewItems(items: List<Item>): MutableList<Item> = itemRepository.saveAll(items)

    @Transactional
    fun updateItem(id: UUID, request: ItemRequest) {
        val savedItem = itemRepository.findByIdOrThrow(id, "존재하지 않는 상품입니다.")
        savedItem.change(request)
    }

    @Transactional(readOnly = true)
    fun getItems(): List<Item> = itemRepository.findAll()

    @Transactional
    fun restock(id: UUID, quantity: Int, requestor: String) {
        val savedItem = itemRepository.findByIdOrThrow(id, "존재하지 않는 상품입니다.")
        savedItem.addStock(quantity, requestor)
    }

    @Transactional
    fun sell(id: UUID, quantity: Int, requestor: String) {
        val savedItem = itemRepository.findByIdOrThrow(id, "존재하지 않는 상품입니다.")
        savedItem.removeStock(quantity, requestor)
    }
}