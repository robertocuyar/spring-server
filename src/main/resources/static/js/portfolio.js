const para = document.createElement("p");

const iconChange = (selector, text)=> {
    const htmlIcon = document.querySelectorAll(selector);

    htmlIcon.forEach((icon) => {
        icon.addEventListener("mouseover", () => {
            icon.firstElementChild.classList.add("d-none");
            icon.appendChild(para)
            para.innerText = text;
        })
        icon.addEventListener("mouseleave", () => {
            icon.firstElementChild.classList.remove("d-none");
            icon.removeChild(para);
        })
    })
}

iconChange(".html-parent", "HTML5");
iconChange(".css-parent", "CSS");
iconChange(".js-parent", "Java Script");
iconChange(".boot-parent", "Bootstrap");
iconChange(".spring-parent", "Spring Boot");
iconChange(".react-parent", "React.js");
iconChange(".java-parent", "Java");

