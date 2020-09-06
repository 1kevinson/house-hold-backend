package com.project.household.api.Repository.Bill;

import java.util.List;

import com.project.household.api.Entity.Bill;

public interface BillRepositorySQLInterface {
	List<Bill> fetchTenantBills(Integer userId);
}
