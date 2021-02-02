const htmlPara = document.createElement("p");


const htmlIcon = document.querySelectorAll(".html-parent");

htmlIcon.forEach((icon)=>{
    icon.addEventListener("mouseover", ()=>{
       icon.firstElementChild.classList.add("d-none");
       icon.appendChild(htmlPara)
        htmlPara.innerText="HTML5";
})
        icon.addEventListener("mouseleave", ()=>{
    icon.firstElementChild.classList.remove("d-none");
    icon.removeChild(htmlPara);
    })
})

