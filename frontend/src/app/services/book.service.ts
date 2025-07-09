import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../models/book.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl = 'http://localhost:8085/api/books';

  constructor(private http: HttpClient) { }

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/getbooks`);
  }

  addBookWithImage(book: Book, image: File): Observable<any> {
    const formData = new FormData();
    formData.append('book', JSON.stringify(book));
    formData.append('image', image);
    return this.http.post(`${this.baseUrl}/postbookwithimage`, formData);
  }

  getBookImage(id: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/image/${id}`, { responseType: 'blob' });
  }

  searchBooks(keyword: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}/search?keyword=${keyword}`);
  }

  getBooksPaginated(page: number, size: number): Observable<any> {
  return this.http.get(`${this.baseUrl}?page=${page}&size=${size}`);
}
}
