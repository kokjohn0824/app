package com.finalpretty.app.product.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.finalpretty.app.Response.ProductDto;
import com.finalpretty.app.model.Product;
import com.finalpretty.app.product.service.ProductService;
import com.finalpretty.app.repositories.Order_detailRespository;
import com.finalpretty.app.repositories.ProductRespository;

@Controller
// @RequestMapping("/admin")
public class ProductController {

	@Autowired
	private ProductService pService;

	@Autowired
	private Order_detailRespository detailDao;

	@Autowired
	private ProductRespository productRespository;

	@GetMapping("/admin/findAllProduct")
	public String findAllproduct() {
		return "product/productAll";
	}

	@GetMapping("/admin/gotoProduct")
	public String addProduct() {
		return "product/addproduct";
	}

	// 新增商品
	@ResponseBody
	@PostMapping("/admin/api/addProduct")
	public Boolean addProduct(@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "type", required = false) String type,
			@RequestParam(name = "price", required = false) Integer price,
			@RequestParam(name = "stock", required = false) Integer stock,
			@RequestParam(name = "text", required = false) String text,
			@RequestParam(name = "onsale", required = false) Integer onsale,
			@RequestParam(name = "file", required = false) MultipartFile file, Model m) {

		Product product = new Product();

		// System.out.println(pService.findDefault(1).getPhoto());
		try {
			product.setTitle(title);
			product.setType(type);
			product.setPrice(price);
			product.setStock(stock);
			product.setVolume(0);
			if (text == null || ("").equals(text)) {
				product.setText("此產品暫無簡介");
			} else {
				product.setText(text);
			}
			if (price == 0 || stock == 0) {
				product.setOnsale(0);
			} else {
				product.setOnsale(onsale);
			}
			if (file == null) {
				// product.setPicture(pService.findDefault(1).getPhoto());
				String saveFiledir = System.getProperty("user.dir") + "/src/main/resources/static/img/lv1.png";
				File saveFilePath = new File(saveFiledir);

				product.setPicture(Files.readAllBytes(saveFilePath.toPath()));
			} else {
				product.setPicture(file.getBytes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		pService.insert(product);

		return true;

	}

	// 後台商品管理
	@GetMapping("/admin/listProduct")
	public String getAllProduct(Model model) {
		List<Product> list = pService.findAll();
		// List<ProductDto> listDto = new ArrayList<>();
		// ProductDto pDto = null;
		// for (Product i : list) {
		// pDto = new ProductDto();
		// pDto.setProduct_id(i.getProduct_id());
		// pDto.setTitle(i.getTitle());
		// pDto.setPrice(i.getPrice());
		// pDto.setType(i.getType());
		// pDto.setStock(i.getStock());
		// pDto.setOnsale(i.getOnsale());
		// pDto.setText(i.getText());
		// listDto.add(pDto);
		// }
		model.addAttribute("productList", list);

		return "/product/productAll";
	}

	@GetMapping("/admin/api/listProduct")
	@ResponseBody
	public List<ProductDto> getAllProduct() {
		List<ProductDto> l = new ArrayList<>();
		pService.findAll().forEach((e) -> {
			l.add(new ProductDto(e.getProduct_id(), e.getTitle(), e.getType(), e.getText(), e.getOnsale(),
					e.getVolume(), e.getStock(), e.getPrice()));
		});
		return l;
	}

	// 商品修改查詢
	@ResponseBody
	@GetMapping("/admin/updateProduct")
	public ProductDto updateQuery(@RequestParam("product_id") Integer id) {
		Product product = pService.findById(id);
		ProductDto pDto = new ProductDto();
		pDto.setProduct_id(product.getProduct_id());
		pDto.setTitle(product.getTitle());
		pDto.setPrice(product.getPrice());
		pDto.setStock(product.getStock());
		pDto.setText(product.getText());
		pDto.setOnsale(product.getOnsale());
		// m.addAttribute("product", product);
		return pDto;
	}

	// 商品修改
	@ResponseBody
	@PostMapping("/admin/api/updateProduct")
	public Boolean updateProduct(@RequestParam(name = "product_id") Integer product_id,
			@RequestParam(name = "title") String title, @RequestParam(name = "type") String type,
			@RequestParam(name = "price") Integer price, @RequestParam(name = "stock") Integer stock,
			@RequestParam(name = "text") String text, @RequestParam(name = "onsale") Integer onsale,
			@RequestParam(name = "file", required = false) MultipartFile file) {

		System.out.println("名稱" + title);
		System.out.println("種類" + type);
		System.out.println("id" + product_id);
		System.out.println("價錢" + price);
		System.out.println("簡介" + text);

		// String title = product.getTitle();
		// Integer price = product.getPrice();
		// Integer stock = product.getStock();
		// String type = product.getType();
		onsale = 0;
		System.out.println("++++++++++++++++++++++++++");
		System.out.println(file);
		// String text = product.getText();
		byte[] picture = null;
		try {
			if (file == null) {
				// picture = pService.findById(product.getProduct_id()).getPicture();
				picture = pService.findById(product_id).getPicture();
			} else {
				picture = file.getBytes();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Integer product_id = product.getProduct_id();
		System.out.println(picture);
		pService.updateProduct(title, price, stock, type, onsale, text, picture, product_id);

		return true;
	}

	// 商品刪除
	@ResponseBody
	@GetMapping("/public/api/deleteProduct")
	public Boolean deleteProduct(@RequestParam("product_id") Integer product_id) {
		if (detailDao.findByFkProductId(product_id).isEmpty()) {
			pService.deleteProduct(product_id);
			return true;
		} else {
			return false;
		}

	}

	@GetMapping("/admin/downloadImage/{id}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
		System.out.println(id);
		Product product = pService.findById(id);

		byte[] photoFile = product.getPicture();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
	}

	// 商品上下架
	@ResponseBody
	@PostMapping("/admin/api/changeOnsale/{product_id}/{onsale}")
	public Product updateOnsale(@PathVariable Integer onsale, @PathVariable Integer product_id, Model m) {
		// Product product = pService.findById(product_id);
		// System.out.println(product);
		// if(product == null) {
		// return null;
		// }
		// System.out.println("one" + product.getOnsale());
		if (onsale == 1) {
			pService.updateOnsale(0, product_id);
		} else if (onsale == 0) {
			pService.updateOnsale(1, product_id);
		}
		Product product = pService.findById(product_id);
		System.out.println("two" + product.getOnsale());

		m.addAttribute("product", product);
		return product;
		// return "redirect:/public/listProduct";
	}

	@ResponseBody
	@PostMapping("/public/api/selectLike")
	public List<Product> selectLike(@RequestBody Map<String, String> searchInput) {
		// System.out.println("++++++++++++++++++++++++++++++++++");
		// System.out.println(productname);
		// System.out.println("++++++++++++++++++++++++++++++++++");

		return pService.selectLike(searchInput.get("searchInput"));
	}

	@ResponseBody
	@PostMapping("/public/api/selectLike2")
	public List<Product> selectLike2(@RequestBody Map<String, String> searchInput) {
		return productRespository.selectLike(searchInput.get("searchInput"));
	}

}
