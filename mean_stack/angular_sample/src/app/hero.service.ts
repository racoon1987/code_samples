import { Injectable } from '@angular/core';
import { HEROES } from './mock-heroes';
import { Hero } from './hero';
import { Headers, Http } from '@angular/http';

// import 'rxjs/add/operator/toPromise';

@Injectable()
export class HeroService {

    private headers = new Headers({ 'Content-Type': 'application/json' });

    private heroesUrl = 'http://localhost:3000/api/heroes';  // URL to web api

    constructor(private http: Http) { }

    getHeroesSlowly(): Promise<Hero[]> {
        return this.http.get(this.heroesUrl)
            .toPromise()
            .then(response => {
                const hero = response.json().data;
                return hero;
            })
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getHeroes(): Hero[] {
        return HEROES;
    }

    getHero(id: number): Promise<Hero> {
        const url = `${this.heroesUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => {
                const hero = response.json().data[0];
                console.log(hero);
                return hero;
            })
            .catch(this.handleError);
    }

    update(hero: Hero): Promise<Hero> {
        const url = `${this.heroesUrl}/${hero.id}`;
        return this.http
            .put(url, JSON.stringify(hero), { headers: this.headers })
            .toPromise()
            .then(() => hero)
            .catch(this.handleError);
    }
}