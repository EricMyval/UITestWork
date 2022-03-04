# UITestWork

Тестовое задание

Чтобы добавить новые кнопки в меню необходимо добавить еще блок кнопок. Контрол сам будет расширяться до нужного размера в зависимости от количество кнопок.
Никаких сторонних библиотек не использовалось. 

        <TableLayout
            android:id="@+id/tlTable2"
            style="@style/MenuBackStyle">

            ...

            // Блок с кнопками
            <TableRow style="@style/TableRowStyle">
                <com.google.android.material.floatingactionbutton.FloatingActionButton
                    android:id="@+id/btnAirplane"
                    android:src="@drawable/airplane"
                    style="@style/TableButtonStyle"
                    tools:ignore="ContentDescription" />
                <com.google.android.material.floatingactionbutton.FloatingActionButton
                    android:id="@+id/btnMobile"
                    style="@style/TableButtonStyle"
                    android:src="@drawable/mobile_data"
                    tools:ignore="ContentDescription" />
            </TableRow>

        </TableLayout>
