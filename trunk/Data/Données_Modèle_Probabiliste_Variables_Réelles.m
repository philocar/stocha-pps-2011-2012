%********************************************************************
%                                                                   *
%   Projet Programmation Stochastique 2011 :                        *
%                                                                   *
%   Management de la production électrique                          *
%                                                                   *
%   Données pour le modèle probabiliste à variables réelles         *
%                                                                   *
%   optimisation sous contraintes en probabilités individuelles :   *
%   Résolution SOCP gestion de la production électrique             *
%                                                                   *
%   Modèle à 7 pas de temps :                                       * 
%   4 unités de production thermique :                              *
%       1 nucléaire, 1 charbon, 1 fioul, 1 gaz                      *
%   1 unité de production hydraulique :                             *
%       1 réservoir et 1 turbine                                    *
%                                                                   *
%   Riadh Zorgati, Novembre 2011                                    *
%                                                                   *
%                                                                   *
%********************************************************************

clear;clc

m=7;
n=5;

% valeurs de la fonction inverse de répartition (Phi inverse) correspondant 
% aux niveaux de probabilité à chaque pas de temps pour l'équilibre
% offre-demande p=[0.96 0.96 0.96 0.96 0.95 0.94 0.93]

phinv=[1.7507 1.7507 1.7507 1.7507 1.6449 1.5548 1.4758];

% Demande : Valeur moyenne bmoy et écart-type sigab

bmoy=[1492 1454 1207 1221 1155 1349 1348];
sigb=[59.68 43.62 24.14 12.21 46.20 40.47 40.44];

% PRODUCTION THERMIQUE

%   Coûts thermiques

cth=[30 45 53 58];

% Coefficients de disponibilité des usines thermiques

% Centrale 1 : nucléaire 900 MW : Valeur moyenne amoy1 et écart-type siga1

amoy1=[0.84 0.86 0.79 0.81 0.64 0.79 0.80];
siga1=[0.0336 0.0344 0.0316 0.0243 0.0192 0.0237 0.0320];

% Centrale 2 : charbon 650 MW : Valeur moyenne amoy2 et écart-type siga2

amoy2=[0.79 0.78 0.73 0.68 0.73 0.78 0.79];
siga2=[0.0158 0.0156 0.0146 0.0136 0.0073 0.0078 0.0158];

% Centrale 3 Fioul 250 MW : Valeur moyenne amoy3 et écart-type siga3

amoy3=[0.82 0.83 0.70 0.59 0.73 0.77 0.79];
siga3=[0.0328 0.0415 0.0210 0.0118 0.0219 0.0154 0.0316];

% Centrale 4 : Gaz 100 MW  : Valeur moyenne amoy4 et écart-type siga4

amoy4=[0.85 0.84 0.85 0.85 0.83 0.81 0.85];    
siga4=[0.0170 0.0252 0.0170 0.0170 0.0083 0.0162 0.0255];

% Capacité max de production thermique : 7 pas de temps

x1max=[890 850 800 800 700 850 900];
x2max=[620 580 520 580 650 600 500];
x3max=[250 230 240 200 150 200 220];
x4max=[90 100 100 100 100 100 100];

% PRODUCTION HYDRAULIQUE

% coût hydraulique

w=38;

% Paramètres réservoir

v0=3000;
vmin=[500 700 500 200 200 500 500];
vmax=[6800 6500 6400 6300 6400 6600 6800];

% Paramètres turbine

rau=0.65;

% Capacité max de production hydraulique : 7 pas de temps (Turbinés max)

x5max = [180 180 170 160 185 175 190];

% Apports hydrauliques ah

ah=[76 83 97 122 127 96 71];

% Variable sur coefficient de la matrice relatif à l'hydraulique

siga5=zeros(1,m);
