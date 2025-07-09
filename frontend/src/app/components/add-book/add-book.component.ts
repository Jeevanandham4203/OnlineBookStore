import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent {
  book: Book = {
    title: '',
    author: '',
    price: 0,
    quantity: 0
  };

  selectedFile: File | null = null;

  constructor(
    private bookService: BookService,
    private router: Router
  ) {}

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file && file.size > 10 * 1024 * 1024) { // 10 MB limit
      alert('Image is too large. Max size is 10MB.');
      return;
    }
    this.selectedFile = file;
  }

  onSubmit(): void {
    if (!this.selectedFile) {
      alert('Please select an image file');
      return;
    }

    this.bookService. addBookWithImage(this.book, this.selectedFile).subscribe({
      next: (res) => {
        console.log('Success Response:', res);
        alert('Book added successfully');
        this.router.navigate(['/bookstore']);
      },
      error: (err) => {
        console.error('Error adding book:', err);
        alert('Failed to add book');
      }
    });
  }
}
