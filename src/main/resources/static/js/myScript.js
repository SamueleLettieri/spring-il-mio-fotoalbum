elencoPhoto();
 


function elencoPhoto(){
    axios.get("http://localhost:8080/api/photo")
    .then((result) => {
        console.log(result.data);
        document.querySelector("#photo").innerHTML += '';
        result.data.forEach(photo => {
            document.querySelector("#photo").innerHTML += `
                <div id="selezione" class="ls_pad card col-3 mx-4">
                    <img class=" card-img-top" src="${photo.url}" alt="Card image cap">
                    <div class="card-body p-3 row text-center justify-content-center">
                    	<div class="col-12">
	                        <h5 class="card-title title">${photo.title}</h5>
	                        <p class="card-text">${photo.description}</p>
	                        <h6 class="tags">${photo.tag}</h6>
	                    </div>    
	                    <div class="row text-center align-items-end  mt-3">
                            <div class="col-12"><a href="/frontend/show?id=${photo.id}" class="btn btn-primary">Dettagli</a></div>
                        </div>
                    </div>
                </div>

            `;
        });
    }).catch((error) => {
        console.warn(error)
    })
    
}

function searchPhotos() {
    const input = document.querySelector('#search');
    const filter = input.value.toUpperCase();
    const photoListContainer = document.querySelector('#photo');
    const photos = photoListContainer.querySelectorAll('.ls_pad.card.col-3.mx-4');
  
    console.log(photos);
    photos.forEach((photo) => {
      const name = photo.querySelector('.title').textContent.toUpperCase();
      const tag = photo.querySelector('.tags').textContent.toUpperCase();
  
      if (name.includes(filter) || tag.includes(filter)) {
        photo.classList.remove('d-none');
      } else {
        photo.classList.add('d-none');
      }
    });
}
  


document.querySelector('#search').addEventListener('input', () => {
    searchPhotos();
});