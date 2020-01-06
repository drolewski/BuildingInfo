import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BuildingsEditorComponent } from './forms/buildings-editor/buildings-editor.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './modules/material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { BuildingEditorComponent } from './forms/building-editor/building-editor.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RoomEditorComponent } from './forms/room-editor/room-editor.component';
import { LevelEditorComponent } from './forms/level-editor/level-editor.component';
import { BuildingsInfoComponent } from './forms/buildings-info/buildings-info.component';
import { BuildingInfoComponent } from './forms/building-info/building-info.component';
import { LevelInfoComponent } from './forms/level-info/level-info.component';
import { RoomInfoComponent } from './forms/room-info/room-info.component';

@NgModule({
  declarations: [
    AppComponent,
    BuildingsEditorComponent,
    BuildingEditorComponent,
    RoomEditorComponent,
    LevelEditorComponent,
    BuildingsInfoComponent,
    BuildingInfoComponent,
    LevelInfoComponent,
    RoomInfoComponent
  ],
  imports: [
    // Angular modules
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,

    // Custom modules
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
