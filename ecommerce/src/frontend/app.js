const API = "http://localhost:8080/api";

let carrito = [];

window.onload = () => {
  cargarProductos();
  cargarPedidos();
  actualizarUI();
};

function cargarProductos() {
  console.log("entrando a cargarProductos");

  fetch(`${API}/productos`)
    .then(res => {
      console.log("status:", res.status);
      return res.json();
    })
    .then(data => {
      console.log("productos recibidos:", data);

      const cont = document.getElementById("productos");

      if (!cont) {
        console.log("NO EXISTE #productos en HTML");
        return;
      }

      cont.innerHTML = "";

      data.forEach(p => {
        cont.innerHTML += `
          <div class="producto">
            <img src="img/${p.imagen}" alt="${p.nombre}" />
            <strong>${p.nombre}</strong><br>
             ${p.precio}<br>
             Stock: ${p.stock}<br>

            <button onclick='agregar(${JSON.stringify(p)})'>
              Agregar
            </button>
          </div>
        `;
      });
    })
    .catch(err => {
      console.log("Error al cargar productos:", err);
    });
}

function agregar(p) {
  let item = carrito.find(x => x.id === p.id);

  if (item) {
    if (item.cantidad >= p.stock) {
      alert("No hay más stock disponible");
      return;
    }
    item.cantidad++;
  } else {
    carrito.push({
      id: p.id,
      nombre: p.nombre,
      precio: p.precio,
      stock: p.stock,
      cantidad: 1
    });
  }

  actualizarUI();
}

function restar(id) {
  let item = carrito.find(p => p.id === id);
  if (!item) return;

  item.cantidad--;

  if (item.cantidad <= 0) {
    carrito = carrito.filter(p => p.id !== id);
  }

  actualizarUI();
}

function sumar(id) {
  let item = carrito.find(p => p.id === id);
  if (!item) return;

  if (item.cantidad >= item.stock) {
    alert("No hay más stock disponible");
    return;
  }

  item.cantidad++;
  actualizarUI();
}

function eliminar(id) {
  carrito = carrito.filter(p => p.id !== id);
  actualizarUI();
}


function renderCarrito() {
  const cont = document.getElementById("carrito");
  cont.innerHTML = "";

  if (carrito.length === 0) {
    cont.innerHTML = "<p>Carrito vacío</p>";
    return;
  }

  carrito.forEach(item => {
    cont.innerHTML += `
      <div class="carrito-item">
        <p><b>${item.nombre}</b></p>
        <p>Cantidad: ${item.cantidad}</p>
        <p>Subtotal: $${item.cantidad * item.precio}</p>

        <button class="btn-restar" onclick="restar(${item.id})">-</button>
        <button class="btn-sumar" onclick="sumar(${item.id})">+</button>
        <button class="btn-eliminar" onclick="eliminar(${item.id})">X</button>
      </div>
    `;
  });

  cont.innerHTML += `
    <button class="btn-confirmar" onclick="crearPedido()">
      Confirmar Pedido
    </button>
  `;
}

function actualizarTotal() {
  let total = 0;

  carrito.forEach(item => {
    total += item.cantidad * item.precio;
  });

  document.getElementById("total").innerText = total;
}


function actualizarContador() {
  let count = carrito.reduce((acc, item) => acc + item.cantidad, 0);
  document.getElementById("cart-count").innerText = count;
}


function actualizarUI() {
  renderCarrito();
  actualizarContador();
  actualizarTotal();
}

function crearPedido() {
  if (carrito.length === 0) {
    alert("Carrito vacío");
    return;
  }

  const body = {
    lineas: carrito.map(p => ({
      producto: { id: p.id },
      cantidad: p.cantidad
    }))
  };

  fetch(`${API}/pedidos`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(body)
  })
    .then(res => res.json())
    .then(data => {
      alert("Pedido creado ✔");
      console.log(data);

      carrito = [];
      actualizarUI();
    })
    .catch(err => {
      console.log("Error al crear pedido:", err);
      alert("Error al crear pedido");
    });
}

function cargarPedidos() {
  fetch(`${API}/pedidos`)
    .then(res => res.json())
    .then(data => {
      const cont = document.getElementById("pedidos");
      cont.innerHTML = "";

      data.forEach(p => {
        cont.innerHTML += `
          <div class="pedido-card">
            <h3> Pedido #${p.id}</h3>
            <p>${p.fecha}</p>
            <p> Estado: ${p.estado}</p>
            <p> Total: $${p.total}</p>

            <div class="pedido-items">
              ${p.lineas.map(l => `
                <div class="pedido-item">
                  🛍️ ${l.producto?.nombre || "Producto"} |
                  x${l.cantidad} |
                  $${l.precio}
                </div>
              `).join("")}
            </div>
          </div>
        `;
      });
    })    .catch(err => console.log("Error pedidos:", err));
}