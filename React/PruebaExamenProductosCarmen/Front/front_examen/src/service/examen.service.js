import http from './../http-common'; // Ajusta la ruta según corresponda

const productService = {
  getAll: async () => {
    const response = await http.get('/products');  // No es necesario poner la URL completa si usas baseURL
    return response.data;
  },
  create: async (product) => {
    const response = await http.post('/products', product);  // El mismo caso
    return response.data;
  },
  update: async (id, product) => {
    const response = await http.put(`/products/${id}`, product);
    return response.data;
  },
  delete: async (id) => {
    await http.delete(`/products/${id}`);
  },
};

export default productService;
