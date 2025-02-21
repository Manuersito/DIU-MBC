import http from "../http-common";

class ProductoService {
  getAll() {
    return http.get("/products");
  }

  create(data) {
    return http.post("/products", data);
  }

  get(id) {
    return http.get(`/products/${id}`);
  }




  update(id, data) {
    return http.put(`/products/${id}`, data);
  }

  delete(id) {
    return http.delete(`/products/${id}`);
  }


}

export default new ProductoService();