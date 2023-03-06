const URLParams = new URLSearchParams(window.location.search);
const photoId = URLParams.get("id");


axios.get(`http://localhost:8080/api/photo/${photoId}`)
.then((resalt) => {
    let photo = resalt.data;
    console.log(photo);
    document.querySelector("#title").innerHTML += photo.title;

    document.querySelector("#img").innerHTML += `<img src="${photo.url}" alt="" class=" ls_img">` ;

    document.querySelector("#desrizione").innerHTML += photo.description;

    photo.categories.forEach(category => {
        document.querySelector("#categorie").innerHTML += `
        <h6> ${category.name} </h6>
        `;
    });

    document.querySelector("#tag").innerHTML += `${photo.tag}`;

    photo.comments.forEach(comment => {
        document.querySelector("#commenti").innerHTML += `
        <h6 class="ls_m ls_size" id="testo"> ${comment.username} </h6>
        <p class="le_text"> ${comment.text} </p>
        `;

    });
    
}).catch((error) => {
    console.warn(error);
})


function addComment(photoId) {
    
    const username = document.querySelector("#username").value;
    const text = document.querySelector("#text").value;
  
    axios.post(`http://localhost:8080/api/photo/${photoId}/comments`, { username, text })
    .then(response => {
        console.log(username);
        console.log(response.data);
        document.querySelector("#username").value = "";
        document.querySelector("#text").value = "";
        location.href = `/frontend/show?id=${photoId}`;
    })
      .catch(error => {
        console.error("Errore nella richiesta", error);
    });
    
}