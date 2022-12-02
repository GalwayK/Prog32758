package ca.sheridanc.on.galway.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridanc.on.galway.beans.Container;
import ca.sheridanc.on.galway.repository.DatabaseAccess;

@RestController
@RequestMapping("/container")
public class ContainerServiceController
{
	private DatabaseAccess dataAccess;
	
	public ContainerServiceController(DatabaseAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
	@GetMapping("/collection")
	public List<Container> getReadContainers()
	{
		System.out.println("Getting Containers");
		return dataAccess.readContainers();
	}
	
	@GetMapping(value = "/{name}")
	public Container getReadContainerByName(@PathVariable String name)
	{
		return dataAccess.readContainerByName(name);
	}
	
	@GetMapping(value = "box/{id}")
	public Container getReadContainerById(@PathVariable int id)
	{
		return dataAccess.readContainerById(id);
	}
	
	@PostMapping(consumes = "application/json")
	public Integer postCreateContainer(@RequestBody Container container)
	{
		return dataAccess.createContainer(container);
	}
	
	@PutMapping(consumes = "application/json", value = "/containerList")
	public Long putCreateContainers(@RequestBody List<Container> containerList)
	{
		dataAccess.deleteContainers();
		dataAccess.createContainers(containerList);
		return dataAccess.readContainerCount();
	}
	
	@PutMapping(consumes = "application/json", value = "/container")
	public Long putCreateContainer(@RequestBody Container container)
	{
		dataAccess.updateContainer(container);
		return dataAccess.readContainerCount();
	}
	
	@DeleteMapping("/deleteAllContainers")
	public Long deleteContainersList()
	{
		dataAccess.deleteContainers();
		return dataAccess.readContainerCount();
	}
	
	@DeleteMapping("/deleteContainer/{id}")
	public void deleteContainer(@PathVariable int id)
	{
		dataAccess.deleteContainer(id);
	}
	
}
