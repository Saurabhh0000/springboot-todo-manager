package com.springboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.binding.Task;
import com.springboot.entity.TaskEntity;
import com.springboot.entity.UserEntity;
import com.springboot.repository.TaskRepository;
import com.springboot.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TaskController{

	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/task/new")
	public String newTask(Model model)
	{
		model.addAttribute("task", new Task());
		
		return "task-form";
	}
	
	@PostMapping("/task/save")
	public String saveTask(
	        @Valid @ModelAttribute("task") Task task,
	        BindingResult result,    // ✔ MUST come right after @Valid object
	        HttpSession session,
	        Model model, RedirectAttributes redirectAttrs) {

	    if (result.hasErrors()) {
	        return "task-form";  // show validation errors
	    }

	    Integer userId = (Integer) session.getAttribute("loggedUser");
	    UserEntity user = userRepo.findById(userId).orElse(null);

	    TaskEntity entity = new TaskEntity();
	    entity.setName(task.getName());
	    entity.setDescription(task.getDescription());
	    entity.setDate(task.getDate());
	    entity.setTimings(task.getTimings());
	    entity.setStatus("In Progress");
	    entity.setUser(user); // MUST BE setUser()

	    taskRepo.save(entity);
	    redirectAttrs.addFlashAttribute("msg", "🎉 Your task has been added successfully!");

	    return "redirect:/dashboard";
	}


	@GetMapping("/dashboard")
	public String showDashboard(@RequestParam(defaultValue = "0") int page,
	                            HttpSession session, Model model) {

	    Integer userId = (Integer) session.getAttribute("loggedUser");
	    String name = (String) session.getAttribute("loggedUserName");

	    if (userId == null) {
	        return "redirect:/login";
	    }

	    model.addAttribute("name", name);

	    // PAGINATION → fetch ONLY 3 tasks per page
	    PageRequest pageable = PageRequest.of(page, 5);
	    Page<TaskEntity> taskPage = taskRepo.findByUserUserId(userId, pageable);

	    List<TaskEntity> paginatedTasks = taskPage.getContent(); // USE THIS

	    // Update status based on date
	    LocalDate today = LocalDate.now();
	    for (TaskEntity task : paginatedTasks) {
	        if (!task.getStatus().equals("Completed")) {
	            if (task.getDate().isBefore(today)) {
	                task.setStatus("Pending");
	            } else {
	                task.setStatus("In Progress");
	            }
	        }
	    }

	    // 🟢 Send PAGINATED tasks to UI
	    model.addAttribute("recentTasks", paginatedTasks);

	    // Pagination metadata
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", taskPage.getTotalPages());

	    // Counts must be non-paginated
	    int completed = taskRepo.findByUserUserIdAndStatus(userId, "Completed").size();
	    int pending = taskRepo.findByUserUserIdAndStatus(userId, "Pending").size();
	    int total = taskRepo.findByUserUserId(userId).size();

	    model.addAttribute("completedTasks", completed);
	    model.addAttribute("pendingTasks", pending);
	    model.addAttribute("totalTasks", total);

	    return "dashboard";
	}
	
	@GetMapping("/tasks")
	public String allTasks(@RequestParam(defaultValue = "0") int page, 
	                       HttpSession session, Model model) {

	    Integer userId = (Integer) session.getAttribute("loggedUser");

	    if (userId == null) {
	        return "redirect:/login";
	    }

	    // Fetch paginated records → 2 per page
	    PageRequest pageable = PageRequest.of(page, 7);
	    Page<TaskEntity> taskPage = taskRepo.findByUserUserId(userId, pageable);

	    List<TaskEntity> paginatedTasks = taskPage.getContent();

	    // Send ONLY paginated data
	    model.addAttribute("tasks", paginatedTasks);

	    // Pagination info
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", taskPage.getTotalPages());

	    return "tasks";
	}

	@GetMapping("/task/edit/{id}")
	public String editTask(@PathVariable Integer id, Model model) {

	    TaskEntity entity = taskRepo.findById(id).orElse(null);

	    Task task = new Task();
	    task.setTaskId(entity.getTaskId());
	    task.setName(entity.getName());
	    task.setDescription(entity.getDescription());
	    task.setDate(entity.getDate());
	    task.setTimings(entity.getTimings());
	    task.setStatus(entity.getStatus());

	    model.addAttribute("task", task);

	    return "task-edit";
	}
	@PostMapping("/task/updateStatus")
	public String updateTaskStatus(@ModelAttribute Task task, RedirectAttributes redirectAttrs) {

	    TaskEntity entity = taskRepo.findById(task.getTaskId()).orElse(null);

	    entity.setStatus(task.getStatus());
	    taskRepo.save(entity);

	    redirectAttrs.addFlashAttribute("msg", "🎉 Task status updated!");

	    return "redirect:/dashboard";
	}


}
