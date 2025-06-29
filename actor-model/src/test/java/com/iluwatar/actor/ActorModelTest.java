/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.actor;

import static org.junit.jupiter.api.Assertions.*;

import com.iluwatar.actormodel.ActorSystem;
import com.iluwatar.actormodel.App;
import com.iluwatar.actormodel.ExampleActor;
import com.iluwatar.actormodel.ExampleActor2;
import com.iluwatar.actormodel.Message;
import org.junit.jupiter.api.Test;

public class ActorModelTest {
  @Test
  void testMainMethod() throws InterruptedException {
    App.main(new String[] {});
  }

  @Test
  public void testMessagePassing() throws InterruptedException {
    ActorSystem system = new ActorSystem();

    ExampleActor srijan = new ExampleActor(system);
    ExampleActor2 ansh = new ExampleActor2(system);

    system.startActor(srijan);
    system.startActor(ansh);

    // Ansh receives a message from Srijan
    ansh.send(new Message("Hello ansh", srijan.getActorId()));

    // Wait briefly to allow async processing
    Thread.sleep(200);

    // Check that Ansh received the message
    assertTrue(
        ansh.getReceivedMessages().contains("Hello ansh"),
        "ansh should receive the message from Srijan");

    // And Srijan should not receive any message back
    assertTrue(
        srijan.getReceivedMessages().isEmpty(),
        "srijan should not receive a reply from ansh");
  }
}
