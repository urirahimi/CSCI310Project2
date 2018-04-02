/**
 * Authentication functions.
 *
 * @author Eddo W. Hintoso
 * @since 01 April, 2018
 */

import { config } from './config';

import * as firebase from 'firebase';

// [START User]
export class User {

  static get ref () {
    return firebase.database().ref().child('users');
  }

  static create (email, password) {
    return firebase.auth()
      .createUserWithEmailAndPassword(email, password)
      .then(() => {
        return new Promise((resolve, reject) => {
          firebase.auth().onAuthStateChanged(user => {
            if (user) {
              console.log("Registering user " + user.uid + " with email <" + email + ">....");
              User.ref.child(user.uid).set({
                  email: email,
                })
                .then(() => {
                  console.log("Registering user " + user.uid + " with email <" + email + "> complete.");
                  resolve(user);
                });
            }
            else {
              // no user is signed in
              console.log("Unfortunately, no user is signed in.");
              reject();
            }
          });
        });
      });
  }

  /**
   * Sign in with email and password.
   */
  static signIn (email, password) {
    return firebase.auth().signInWithEmailAndPassword(email, password).then(() => {
      return new Promise((resolve, reject) => {
        firebase.auth().onAuthStateChanged(user => {
          if (user) {
            console.log("Successfully signed in user <" + user.uid + "> with email <" + email + ">.");
            resolve(user);
          }
          else {
            // no user is signed in
            console.log("Unfortunately, no user is signed in.");
            reject();
          }
        });
      });
    });
  }

}
// [END User]
