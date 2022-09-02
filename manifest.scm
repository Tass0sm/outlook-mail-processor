(use-modules (tassos-guix develop)
             (gnu packages guile)
             (guix gexp)
             (guix packages)
             (guix download)
             (guix git-download)
             (guix build-system clojure)
             ((guix licenses) #:prefix license:))

(define %source-dir
  (dirname (current-filename)))

(define mail-proc
  (package
   (name "mail-proc")
   (version "0.0.1")
   (source
    (local-file %source-dir
                #:recursive? #t
                #:select? (git-predicate %source-dir)))
   (build-system clojure-build-system)
   (home-page "")
   (synopsis "")
   (description "")
   (license license:gpl3+)))

(de->manifest
 (development-environment
  (package mail-proc)))
