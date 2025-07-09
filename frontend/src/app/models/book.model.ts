export interface Book {
    id?: number;
    title: string;
    author: string;
    price: number;
    quantity: number;
    image?: any; // For BLOB image preview
  }
  