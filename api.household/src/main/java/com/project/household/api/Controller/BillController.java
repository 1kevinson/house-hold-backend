package com.project.household.api.Controller;

//Always imports those two for generated HATEAOS links
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.household.api.Assembler.BillModelAssembler;
import com.project.household.api.Entity.Bill;
import com.project.household.api.Exception.NotFound.BillNotFoundException;
import com.project.household.api.Repository.Bill.BillRepository;

@RestController
@RequestMapping("/api")
public class BillController {

	@Autowired
	private BillRepository billRepository;
	@Autowired
	private BillModelAssembler billModelAssembler;

	// Get all bills
	@GetMapping("/bills")
	public CollectionModel<EntityModel<Bill>> getAllBills() {
		List<EntityModel<Bill>> bills = billRepository.findAll().stream() //
				.map(billModelAssembler::toModel) //
				.collect(Collectors.toList());
		return CollectionModel.of(bills, linkTo(methodOn(BillController.class).getAllBills()).withSelfRel());
	}

	// Get one bill
	@GetMapping("/bills/{id}")
	public EntityModel<Bill> getOneBill(@PathVariable Integer id) {
		Bill bill = billRepository.findById(id).orElseThrow(() -> new BillNotFoundException(id));
		return billModelAssembler.toModel(bill);
	}

	// Add a new bill
	@PostMapping("/bills")
	public ResponseEntity<?> addBill(@RequestBody Bill newBill) {
		EntityModel<Bill> entityModel = billModelAssembler.toModel(billRepository.save(newBill));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Update a bill
	@PutMapping("/bills/{id}")
	public ResponseEntity<?> replaceBill(@RequestBody Bill newBill, @PathVariable Integer id) {
		Bill updateBill = billRepository.findById(id).map(bill -> {
			return billRepository.save(bill);
		}).orElseGet(() -> {
			newBill.setId(id);
			return billRepository.save(newBill);
		});

		EntityModel<Bill> entityModel = billModelAssembler.toModel(updateBill);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Delete one bill
	@DeleteMapping("/bills/{id}")
	public ResponseEntity<?> deleteBill(@PathVariable Integer id) {
		billRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
