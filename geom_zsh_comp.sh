function _geom(){

  local line

  _arguments -C \
      "-h[Show help information]" \
      "--h[Show help information]" \
      "1: :(buffer centroid)" \
      "*::arg:->args"

  case $line[1] in
      buffer)
          _buffer
      ;;
      centroid)
          _centroid
      ;;
  esac

#  _describe 'command' "('buffer:Buffer a geometry' 'centroid:Calculate the centroid')"

  return 0
}

function _buffer {
    _arguments \
        '(-g --geometry)'{-g,--geometry}'[The input geometry]' \
        '(-d --distance)'{-d,--distance}'[The buffer distance]:float:'
}

function _centroid {
    _arguments \
        "--geometry[The input geometry]"
}

compdef _geom geom