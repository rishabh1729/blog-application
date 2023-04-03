import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostPayload } from './model/post-payload.model';

@Injectable({
  providedIn: 'root'
})
export class AddPostService {

  private url = 'http://localhost:8989/api/posts/';
  constructor(private httpClient: HttpClient) { }

  addPost(postPayload: PostPayload) {
    return this.httpClient.post(this.url, postPayload);
  }

  getAllPosts() {
    return this.httpClient.get<Array<PostPayload>>(this.url + 'all');
  }

  getPost(id: number) {
    return this.httpClient.get<PostPayload>(this.url + 'get/' + id);
  }
}
