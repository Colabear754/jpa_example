package com.colabear754.jpa_example.controllers.item

import com.colabear754.jpa_example.dto.item.ItemRequest
import com.colabear754.jpa_example.services.item.ItemService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item")
class ItemController(private val itemService: ItemService) {
    @GetMapping("/list")
    fun list() = itemService.getItems()

    @PostMapping("/new")
    fun registItem(@RequestBody item: ItemRequest) = itemService.registNewItem(item)
}