/**
 * Copyright 2009-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.javacrumbs.futureconverter.springrx;

import org.springframework.util.concurrent.ListenableFuture;
import rx.Observable;

public class FutureConverter {

    /**
     * Converts Observable to ListenableFuture.
     *
     * @param listenableFuture
     * @param <T>
     * @return
     */
    public static <T> Observable<T> toObservable(ListenableFuture<T> listenableFuture) {
        if (listenableFuture instanceof ObservableListenableFuture) {
            return ((ObservableListenableFuture) listenableFuture).getObservable();
        } else {
            return new ListenableFutureObservable<>(listenableFuture);
        }

    }

    /**
     * Converts observable to ListenableFuture. Warning: modifies the original observable.
     *
     * @param observable
     * @param <T>
     * @return
     */
    public static <T> ListenableFuture<T> toListenableFuture(Observable<T> observable) {
        if (observable instanceof ListenableFutureObservable) {
            return ((ListenableFutureObservable) observable).getListenableFuture();
        } else {
            return new ObservableListenableFuture(observable);
        }
    }

}
