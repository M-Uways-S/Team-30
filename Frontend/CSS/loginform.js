const signInButton = document.getElementById("sign-in-button");
const signUpButton = document.getElementById("sign-up-button");
const signInForm = document.getElementById("sign-in-form");
const signUpForm = document.getElementById("sign-up-form");

signUpButton.addEventListener("click", function(){
    signInForm.style.display = "none";
    signUpForm.style.display = "block";
});

signInButton.addEventListener("click", function(){
    signUpForm.style.display = "none";
    signInForm.style.display = "block";
});
