<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRM</title>
</head>
<body>
	<h1>Customer Form</h1>
	<form id="cusForm" method="post" action="api/customers">
		<div>
			<span>Name</span><span><input type="text" id="name" name="name"></span>
		</div>
		<div>
			<span>Address</span><span><input type="text" id="address" name="address"></span>
		</div>
		<div>
			<span>Phone Number</span><span><input type="text"
				id="phoneNumber" name="phoneNumber"></span>
		</div>
		<div>
			<span>Status</span><span><input type="text" id="status" name="status"></span>
		</div>
		<div>
			<input type="submit" value="Submit" />
		</div>
	</form>
	<h1>Customer Form with AJAX</h1>
	<form id="cusForm" action="#" onsubmit="addCustomer(this);return false;">
		<div>
			<span>Name</span><span><input type="text" id="name" name="name"></span>
		</div>
		<div>
			<span>Address</span><span><input type="text" id="address" name="address"></span>
		</div>
		<div>
			<span>Phone Number</span><span><input type="text"
				id="phoneNumber" name="phoneNumber"></span>
		</div>
		<div>
			<span>Status</span><span><input type="text" id="status" name="status"></span>
		</div>
		<div>
			<input type="submit" value="Submit" />
		</div>
	</form>
	
	<script type="text/javascript">
	function addCustomer(formObj) {
		var customer = {};
		customer.name = formObj["name"].value;
		customer.address = formObj["address"].value;
		customer.phoneNumber = formObj["phoneNumber"].value;
		customer.status = formObj["status"].value;
		
		var postData = JSON.stringify(customer);
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if ((xhr.readyState == 4) && (xhr.status == 200)) {
				console.log(xhr.responseText);
			}
		};
		
		xhr.open("post", "api/customers", true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.setRequestHeader("Accept", "application/json");
		xhr.send(postData);
	}
	</script>
</body>
</html>