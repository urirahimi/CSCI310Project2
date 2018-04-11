/**
 * Test suite for users
 */
 
import assert from 'assert';
import { User } from './users';
import * as firebase from 'firebase';

// [START UserTestSuite]
export class UserTestSuite {

  static setUp ()
  {
    UserTestSuite.email = "lmfao@usc.edu";
    UserTestSuite.password = "lmfaoLOL";
  }

  static testCreate ()
  {
    return User.create(UserTestSuite.email, UserTestSuite.password);
  }

  static testSignIn ()
  {
    return User.signIn(UserTestSuite.email, UserTestSuite.password);
  }

  static run ()
  {
    UserTestSuite.testCreate()
      .then(() => {
        UserTestSuite.testSignIn().then(user => {
          let uid = user.uid;
          let email = user.email;

          console.log("Deleting user " + uid + " with email <" + email + ">....");
          user.delete()
            .then(() => {
              console.log("Deleting user " + uid + " with email <" + email + "> complete.");
            })
            .catch(err => {
              console.log("Failed to delete user " + uid + " with email <" + email + ">, please try again.");
            });
        });
      })
  }

  static tearDown ()
  {
    let user = firebase.auth().currentUser;
    return user.delete();
  }

}

UserTestSuite.setUp();
UserTestSuite.run();
delete UserTestSuite.setUp;
