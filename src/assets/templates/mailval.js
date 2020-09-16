function validateEmail(emailField){
        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

        if (reg.test(emailField.value) == false) 
        {
        	
        	var elem = document.getElementById('mailCamp');
        	elem.style.color = "red";
        	
            return false;
        }
        var elem = document.getElementById('mailCamp');
    	elem.style.color = "#444";
        return true;

}