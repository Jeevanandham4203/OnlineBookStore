import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { Book } from 'src/app/models/book.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css'],
})
export class BookListComponent implements OnInit {
  books: Book[] = [];

  searchTerm: string = '';
  noResults: boolean = false;

  userName: string = '';

  constructor(private bookService: BookService, private route: Router) {}

  ngOnInit(): void {
    const user = localStorage.getItem('user');
    if (user) {
      const parsed = JSON.parse(user);
      this.userName = parsed.name;
    } else {
      //this.route.navigate(['/login']);
    }
    this.fetchBooks(); //getallbooks
  }

  fetchBooks(): void {
    this.bookService.getAllBooks().subscribe((bookList) => {
      this.books = bookList;

      // Fetch image for each book
      this.books.forEach((book) => {
        if (book.id) {
          this.bookService.getBookImage(book.id).subscribe((img) => {
            const reader = new FileReader();
            reader.readAsDataURL(img);
            reader.onloadend = () => {
              book.image = reader.result;
            };
          });
        }
      });
    });
  }
  getAllBooks(): void {
    this.bookService.getAllBooks().subscribe({
      next: (data) => {
        this.books = data;

        // Load images for each book
        this.books.forEach((book) => {
          if (book.id) {
            this.bookService.getBookImage(book.id).subscribe((img) => {
              const reader = new FileReader();
              reader.readAsDataURL(img);
              reader.onloadend = () => {
                book.image = reader.result;
              };
            });
          }
        });
      },
      error: (err) => {
        console.error('Error loading books:', err);
      },
    });
  }

  onSearch(): void {
    if (this.searchTerm.trim() === '') {
      this.getAllBooks();
      this.noResults = false;
    } else {
      this.bookService.searchBooks(this.searchTerm).subscribe({
        next: (data) => {
          this.books = data;
          this.noResults = data.length === 0;

          this.books.forEach((book) => {
            if (book.id) {
              this.bookService.getBookImage(book.id).subscribe((img) => {
                const reader = new FileReader();
                reader.readAsDataURL(img);
                reader.onloadend = () => {
                  book.image = reader.result;
                };
              });
            }
          });
        },
        error: (err) => {
          this.books = [];
          this.noResults = true;
          console.error('Search error:', err);
        },
      });
    }
  }

  currentPage: number = 1;
  pageSize: number = 4;
  totalItems: number = 4;

  getBooks(): void {
    this.bookService
      .getBooksPaginated(this.currentPage, this.pageSize)
      .subscribe({
        next: (res) => {
          this.books = res.content;
          this.totalItems = res.totalElements;
          // Load images if needed
        },
      });
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.getBooks();
  }

  get pageNumbers(): number[] {
  const totalPages = Math.ceil(this.totalItems / this.pageSize);
  return Array.from({ length: totalPages }, (_, i) => i);
}
}
