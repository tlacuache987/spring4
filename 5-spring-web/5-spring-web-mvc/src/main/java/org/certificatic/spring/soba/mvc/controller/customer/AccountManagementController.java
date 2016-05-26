package org.certificatic.spring.soba.mvc.controller.customer;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.certificatic.spring.soba.domain.Account;
import org.certificatic.spring.soba.domain.Customer;
import org.certificatic.spring.soba.service.account.api.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes({ "userSession", "logableUser" })
@RequestMapping(value = "/customer/manage/accounts")
public class AccountManagementController {

	@Autowired
	private IAccountService accountService;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showManagementAccountsPage(HttpSession session, Model model,
			@ModelAttribute("logableUser") Customer customer) {

		log.info("logable user: {}", customer);

		List<Account> accounts = accountService.getByCustomerId(customer.getId());

		model.addAttribute("accounts", accounts);

		model.addAttribute("currentSecc", "manageAccounts");

		return "customer/manage/accounts";
	}

	@RequestMapping(value = "/view/{accountId}", method = RequestMethod.GET)
	public String showViewAccountPage(HttpSession session, Model model,
			@ModelAttribute("logableUser") Customer customer, @PathVariable Integer accountId) {

		List<Account> accounts = accountService.getByCustomerId(customer.getId());

		Optional<Account> optionalAccount = accounts.stream().filter((a) -> a.getId().equals(new Long(accountId)))
				.findAny();

		if (!optionalAccount.isPresent())
			return "rediect:/customer/manage/accounts";

		model.addAttribute("account", optionalAccount.get());

		model.addAttribute("currentSecc", "manageAccounts");

		return "customer/manage/accounts/view";
	}

	@RequestMapping(value = "/transfer/{accountId}", method = RequestMethod.GET)
	public String showViewTransferAccountPage(HttpSession session, Model model,
			@ModelAttribute("logableUser") Customer customer, @PathVariable Integer accountId) {

		List<Account> accounts = accountService.getByCustomerId(customer.getId());

		Optional<Account> optionalAccount = accounts.stream().filter((a) -> a.getId().equals(new Long(accountId)))
				.findAny();

		if (!optionalAccount.isPresent())
			return "rediect:/customer/manage/accounts";

		model.addAttribute("account", optionalAccount.get());

		model.addAttribute("currentSecc", "manageAccounts");

		return "customer/manage/accounts/transfer";
	}
}
