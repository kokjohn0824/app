package com.finalpretty.app.product.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedAttribute;
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

import com.finalpretty.app.model.Product;
import com.finalpretty.app.product.service.ProductService;
import com.finalpretty.app.repositories.ProductRespository;

@Controller
@RequestMapping("/public")
public class ProductController {

	@Autowired
	private ProductService pService;

	// @Autowired
	// private ProductRespository pDao;

	@GetMapping("/findAllProduct")
	public String findAllproduct() {
		return "product/productAll";
	}

	@GetMapping("/gotoProduct")
	public String addProduct() {
		return "product/addproduct";
	}

	@PostMapping("/addProduct")
	public String addProduct(@RequestParam(name = "title") String title, @RequestParam(name = "type") String type,
			@RequestParam(name = "price") Integer price, @RequestParam(name = "stock") Integer stock,
			@RequestParam(name = "text") String text, @RequestParam(name = "onsale") Integer onsale,
			@RequestParam(name = "file") MultipartFile file) {

		Product product = new Product();

		try {
			product.setTitle(title);
			product.setType(type);
			product.setPrice(price);
			product.setStock(stock);
			product.setText(text);
			product.setOnsale(onsale);
			if (file.getBytes().length == 0) {
				product.setPicture(null);
			} else {
				product.setPicture(file.getBytes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		pService.insert(product);

		return "redirect:/public/listProduct";

	}

	@GetMapping("/listProduct")
	public String getAllProduct(Model model) {
		List<Product> list = pService.findAll();
		for (Product li : list) {
			System.out.println(li.getPicture());
		}
		model.addAttribute("productList", list);

		return "/product/productAll";
	}

	@GetMapping("/updateProduct")
	public String updateQuery(@RequestParam("product_id") Integer id, Model m) {
		Product product = pService.findById(id);
		m.addAttribute("product", product);
		return "product/editProduct";
	}

	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file) {

		System.out.println("名稱" + product.getTitle());
		System.out.println("種類" + product.getType());
		System.out.println("id" + product.getProduct_id());
		System.out.println("價錢" + product.getPrice());
		System.out.println("簡介" + product.getText());

		String title = product.getTitle();
		Integer price = product.getPrice();
		Integer Stock = product.getStock();
		String type = product.getType();
		Integer onsale = product.getOnsale();
		String text = product.getText();
		byte[] picture = null;
		try {
			if (file.getBytes().length == 0) {
				picture = pService.findById(product.getProduct_id()).getPicture();
			} else {
				picture = file.getBytes();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Integer product_id = product.getProduct_id();
		System.out.println(picture);
		pService.updateProduct(title, price, Stock, type, onsale, text, picture, product_id);

		return "redirect:/public/listProduct";
	}

	@GetMapping("/downloadImage/{id}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
		System.out.println(id);
		Product product = pService.findById(id);

		byte[] photoFile = product.getPicture();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("/api/changeOnsale/{product_id}/{onsale}")
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
		if (product != null) {
			return product;
		}
		return null;
		// return "redirect:/public/listProduct";
	}

}
