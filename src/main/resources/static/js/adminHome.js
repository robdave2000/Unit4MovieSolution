//DOM Elements
const submitForm = document.getElementById("movie-form")
const movieContainer = document.getElementById("movie-container")

//Modal Elements
let movieTitle = document.getElementById(`movie-title`)
let updateMovieBtn = document.getElementById('update-movie-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/movies"

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+";expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        title: document.getElementById("movie-input").value
    }
    await addMovie(bodyObj);
    document.getElementById("movie-input").value = ''
}

async function addMovie(obj) {
    const response = await fetch(`${baseUrl}/add`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getMovies();
    }
}

async function getMovies() {
    await fetch(`${baseUrl}/all`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createMovieCards(data))
        .catch(err => console.error(err))
}

async function getMovieById(movieId){
    await fetch(`${baseUrl}/get/${movieId}`, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleMovieEdit(movieId){
    let bodyObj = {
        id: movieId,
        title: movieTitle.value
    }

    await fetch(`${baseUrl}/update`, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getMovies();
}

async function handleDelete(movieId){
    await fetch(`${baseUrl}/delete/${movieId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getMovies();
}

const createMovieCards = (array) => {
    movieContainer.innerHTML = ''
    array.forEach(obj => {
        let movieCard = document.createElement("div")
        movieCard.classList.add("m-2")
        movieCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <p class="card-text">${obj.title}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getMovieById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#movie-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        movieContainer.append(movieCard);
    })
}

const populateModal = (obj) =>{
    movieTitle.innerText = ''
    movieTitle.innerText = obj.title
    updateMovieBtn.setAttribute('data-movie-id', obj.id)
}

getMovies();

submitForm.addEventListener("submit", handleSubmit)

updateMovieBtn.addEventListener("click", (e)=>{
    let movieId = e.target.getAttribute('data-movie-id')
    handleMovieEdit(movieId);
})