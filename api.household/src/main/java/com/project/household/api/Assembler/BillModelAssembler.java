package com.project.household.api.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.project.household.api.Controller.BillController;
import com.project.household.api.Entity.Bill;

@Component
public class BillModelAssembler implements RepresentationModelAssembler<Bill, EntityModel<Bill>> {

	@Override
	public EntityModel<Bill> toModel(Bill bill) {
		return EntityModel.of(bill, //
				linkTo(methodOn(BillController.class).getOneBill(bill.getId())).withSelfRel(),
				linkTo(methodOn(BillController.class).getAllBills()).withRel("bills"));
	}

}
